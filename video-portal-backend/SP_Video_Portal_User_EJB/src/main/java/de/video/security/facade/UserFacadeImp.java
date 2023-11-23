/* (C)2023 */
package de.video.security.facade;

import de.video.security.dao.UserDAO;
import de.video.security.entity.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserFacadeImp implements IUserFacade {

    @Inject UserDAO userDAO;

    public User findUserByName(String name) {
        return userDAO.findUserByName(name);
    }
}
