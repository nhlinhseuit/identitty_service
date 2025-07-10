package com.example.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.identityservice.dto.request.RoleRequest;
import com.example.identityservice.dto.response.RoleResponse;
import com.example.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    // O request chi truyen vao List<String> cac permissionNames, nen phai bien doi them de truyen ve RoleResponse chua
    // List<Permission>
    // ignore de tu build phan permissions truyen vao
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
