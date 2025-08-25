package com.simple_official_spring_template.service;

public interface ITotalUserPermissionService {
    boolean requireMasterRole(String token);
    boolean requireActionName(String token, String actionName);
}
