package eu.europa.esig.dss.validation.process.art32.qualification.checks.filter;

import java.util.List;

import eu.europa.esig.dss.validation.reports.wrapper.TrustedServiceWrapper;

public interface TrustedServiceFilter {

	List<TrustedServiceWrapper> filter(List<TrustedServiceWrapper> trustedServices);

}
