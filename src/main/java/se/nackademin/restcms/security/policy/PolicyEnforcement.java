package se.nackademin.restcms.security.policy;

public interface PolicyEnforcement {

	boolean check(Object subject, Object resource, Object action, Object environment);

}