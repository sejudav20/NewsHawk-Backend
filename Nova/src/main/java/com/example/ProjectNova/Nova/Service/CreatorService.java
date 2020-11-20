package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSContentDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CreatorService {
    //This service provides methods that will aid a creator such as adding new articles and managing views
    AWSUserDAO uDao;
    AWSContentDAO cDao;
    public CreatorService(@Qualifier("UserDao")AWSUserDAO aWSUserDAO, @Qualifier("ContentDao") AWSContentDAO aWSContentDAO) {
        this.uDao=aWSUserDAO;
        this.cDao=aWSContentDAO;
    }



}
