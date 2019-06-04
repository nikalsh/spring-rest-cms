package se.nackademin.restcms.service;

import se.nackademin.restcms.entities.User;

public interface UserService {

    User saveBlogAdmin(User user);

    User getCurrentUser();

    User updatePassword(String password);
}
