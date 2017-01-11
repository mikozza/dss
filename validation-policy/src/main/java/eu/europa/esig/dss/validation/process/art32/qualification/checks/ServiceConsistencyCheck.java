package eu.europa.esig.dss.validation.process.art32.qualification.checks;

import java.util.List;

import eu.europa.esig.dss.jaxb.detailedreport.XmlSignatureAnalysis;
import eu.europa.esig.dss.utils.Utils;
import eu.europa.esig.dss.validation.MessageTag;
import eu.europa.esig.dss.validation.policy.rules.Indication;
import eu.europa.esig.dss.validation.policy.rules.SubIndication;
import eu.europa.esig.dss.validation.process.ChainItem;
import eu.europa.esig.dss.validation.process.art32.qualification.checks.consistency.TrustedServiceChecker;
import eu.europa.esig.dss.validation.reports.wrapper.TrustedServiceWrapper;
import eu.europa.esig.jaxb.policy.LevelConstraint;

public class ServiceConsistencyCheck extends ChainItem<XmlSignatureAnalysis> {

	private final List<TrustedServiceWrapper> trustedServices;

	private MessageTag errorMessage;

	public ServiceConsistencyCheck(XmlSignatureAnalysis result, List<TrustedServiceWrapper> trustedServices, LevelConstraint constraint) {
		super(result, constraint);

		this.trustedServices = trustedServices;
	}

	@Override
	protected boolean process() {

		if (Utils.isCollectionNotEmpty(trustedServices)) {
			for (TrustedServiceWrapper trustedService : trustedServices) {

				if (!TrustedServiceChecker.isQCStatementConsistent(trustedService)) {
					errorMessage = MessageTag.ART32_TL_SERV_CONS_ANS1;
					return false;
				}

				if (!TrustedServiceChecker.isLegalPersonConsistent(trustedService)) {
					errorMessage = MessageTag.ART32_TL_SERV_CONS_ANS2;
					return false;
				}

				if (!TrustedServiceChecker.isQSCDConsistent(trustedService)) {
					errorMessage = MessageTag.ART32_TL_SERV_CONS_ANS3;
					return false;
				}

				if (!TrustedServiceChecker.isUsageConsistent(trustedService)) {
					errorMessage = MessageTag.ART32_TL_SERV_CONS_ANS4;
					return false;
				}
			}
		}

		return true;
	}

	@Override
	protected MessageTag getMessageTag() {
		return MessageTag.ART32_TL_SERV_CONS;
	}

	@Override
	protected MessageTag getErrorMessageTag() {
		return errorMessage;
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
