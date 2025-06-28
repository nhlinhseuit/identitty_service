package com.example.identityservice.mapper;

import com.example.identityservice.dto.request.UserCreationRequest;
import com.example.identityservice.dto.request.UserUpdateRequest;
import com.example.identityservice.dto.response.UserResponse;
import com.example.identityservice.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponse> toUserResponseList(List<User> users);

//    tao ra User moi
    User toUser(UserCreationRequest  request);

//    @Mapping(source = "firstName", target = "lastName")
    @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);

//    Co user, nhan vao user de update lai
    // Mappingtarget: thay vi tao 1 User moi, thi cap nhat truc tiep vao User
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
