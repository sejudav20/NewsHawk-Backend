package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSContentDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import com.example.ProjectNova.Nova.DAO.ContentDAO;
import com.example.ProjectNova.Nova.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service()
public class UserService {
    //this service focuses on user/viewer actions such as adding to favorites, getting recommended articles
        AWSUserDAO uDao;
        AWSContentDAO cDao;
    public UserService(@Qualifier("UserDao")AWSUserDAO aWSUserDAO,@Qualifier("ContentDAO") AWSContentDAO aWSContentDAO) {
        this.uDao=aWSUserDAO;
        this.cDao=aWSContentDAO;
    }

}
