package eu.europa.esig.dss.validation.process.art32.qualification.checks.qualified;

import java.util.Date;
import java.util.List;

import eu.europa.esig.dss.DSSException;
import eu.europa.esig.dss.utils.Utils;
import eu.europa.esig.dss.validation.process.art32.qualification.checks.filter.TrustedServiceFilter;
import eu.europa.esig.dss.validation.process.art32.qualification.checks.filter.TrustedServicesFilterFactory;
import eu.europa.esig.dss.validation.reports.wrapper.CertificateWrapper;
import eu.europa.esig.dss.validation.reports.wrapper.TrustedServiceWrapper;

public class QualificationFromCertAndTL implements QualificationStrategy {

	private final CertificateWrapper signingCertificate;
	private final List<TrustedServiceWrapper> servicesForESign;
	private final Date date;

	public QualificationFromCertAndTL(CertificateWrapper signingCertificate, List<TrustedServiceWrapper> servicesForESign, Date date) {
		this.signingCertificate = signingCertificate;
		this.servicesForESign = servicesForESign;
		this.date = date;
	}

	@Override
	public QualifiedStatus getQualifiedStatus() {

		// 1. filter at date
		TrustedServiceFilter filterByDate = TrustedServicesFilterFactory.createFilterByDate(date);
		List<TrustedServiceWrapper> servicesAtGivenDate = filterByDate.filter(servicesForESign);

		// 2. retrieve certificate qualification from the certificate itself
		QualificationStrategy qualificationStrategy = QualificationStrategyFactory.createQualificationFromCert(signingCertificate);
		QualifiedStatus qualifiedStatusFromSigCert = qualificationStrategy.getQualifiedStatus();

		// 1 TSP and 1 TS // TODO improve
		TrustedServiceWrapper trustedService = getUniqueTrustedService(servicesAtGivenDate);

		// 3. Apply TL overruling(s)
		QualificationStrategy qualificationFromCertAndTL = QualificationStrategyFactory.createQualificationFromTL(trustedService, signingCertificate,
				qualifiedStatusFromSigCert);

		return qualificationFromCertAndTL.getQualifiedStatus();
	}

	private TrustedServiceWrapper getUniqueTrustedService(List<TrustedServiceWrapper> servicesAtGivenDate) {
		int tspsSize = Utils.collectionSize(servicesAtGivenDate);
		if (tspsSize > 1) {
			throw new DSSException("More than one Trusted Service");
		} else if (tspsSize == 1) {
			return servicesAtGivenDate.get(0);
		}
		return null;
	}

}
