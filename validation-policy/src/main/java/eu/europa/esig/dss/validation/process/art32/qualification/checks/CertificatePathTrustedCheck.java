package eu.europa.esig.dss.validation.process.art32.qualification.checks;

import eu.europa.esig.dss.jaxb.detailedreport.XmlSignatureAnalysis;
import eu.europa.esig.dss.validation.MessageTag;
import eu.europa.esig.dss.validation.policy.rules.Indication;
import eu.europa.esig.dss.validation.policy.rules.SubIndication;
import eu.europa.esig.dss.validation.process.ChainItem;
import eu.europa.esig.dss.validation.reports.wrapper.CertificateWrapper;
import eu.europa.esig.jaxb.policy.LevelConstraint;

public class CertificatePathTrustedCheck extends ChainItem<XmlSignatureAnalysis> {

	private final CertificateWrapper signingCertificate;

	public CertificatePathTrustedCheck(XmlSignatureAnalysis result, CertificateWrapper signingCertificate, LevelConstraint constraint) {
		super(result, constraint);
		this.signingCertificate = signingCertificate;
	}

	@Override
	protected boolean process() {
		return signingCertificate != null && signingCertificate.hasTrustedServices();
	}

	@Override
	protected MessageTag getMessageTag() {
		return MessageTag.ART32_TRUSTED_CERT_PATH;
	}

	@Override
	protected MessageTag getErrorMessageTag() {
		return MessageTag.ART32_TRUSTED_CERT_PATH_ANS;
	}

	@Override
	protected Indication getFailedIndicationForConclusion() {
		return Indication.FAILED;
	}

	@Override
	protected SubIndication getFailedSubIndicationForConclusion() {
		return null;
	}

}
