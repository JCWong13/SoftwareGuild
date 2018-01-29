/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.DVD;
import java.util.List;

/**
 *
 * @author janie
 */
public interface DVDCollectionDao {
    
    List<DVD> getAllDVDs()
    throws DVDCollectionDaoException;
    
    DVD addDVD(DVD dvd)
    throws DVDCollectionDaoException;
    
    DVD viewDVDInfo(String dvdTitle)
    throws DVDCollectionDaoException;
    
    DVD editDVD(String dvdTitle)
    throws DVDCollectionDaoException;
    
    void overWriteDVD() 
    throws DVDCollectionDaoException;
    
    DVD removeDVD(String dvdTitle)
    throws DVDCollectionDaoException;
}