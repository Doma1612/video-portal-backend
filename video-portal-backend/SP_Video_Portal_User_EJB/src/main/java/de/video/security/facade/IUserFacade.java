/* (C)2023 */
package de.video.security.facade;

import de.video.security.entity.User;
import jakarta.ejb.Local;

@Local
public interface IUserFacade {

    public User findUserByName(String username);

    public void userAnlegen(User user);
}
