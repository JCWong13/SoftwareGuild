/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.dao;

import com.sg.dvdcollection.dto.DVD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author janie
 */
public class DVDCollectionDaoFileImpl implements DVDCollectionDao {

    private Map<String, DVD> dvds = new HashMap<>();

    public static final String COLLECTION_FILE = "DVDcollection.txt";
    public static final String DELIMITER = "::";

    private void loadDVDCollection() throws DVDCollectionDaoException {//unmarshall DVD collection
        Scanner scanner;

        try {
            scanner = new Scanner(new File(COLLECTION_FILE));//File makes a file object to pass to scanner
        } catch (FileNotFoundException e) {
            throw new DVDCollectionDaoException(
                    "-_- Could not load DVD Collection into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setReleaseDate(currentTokens[1]);
            currentDVD.setMpaaRating(currentTokens[2]);
            currentDVD.setDirectorName(currentTokens[3]);
            currentDVD.setStudio(currentTokens[4]);
            currentDVD.setUserNote(currentTokens[5]);

            dvds.put(currentDVD.getDVDTitle(), currentDVD);

        }
        scanner.close();

    }

    private void writeDVDCollection() throws DVDCollectionDaoException {//marshall DVD collection
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(COLLECTION_FILE));
        } catch (IOException e) {
            throw new DVDCollectionDaoException(
                    "Could not save DVD data.", e);
        }

        List<DVD> dvdList = getAllDVDs();
        for (DVD currentDVD : dvdList) {
            out.println(currentDVD.getDVDTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getUserNote());

            out.flush();
        }
        out.close();
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDCollectionDaoException {
        loadDVDCollection();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public DVD addDVD(DVD dvd)
            throws DVDCollectionDaoException {
        loadDVDCollection();
        DVD newDVD = dvds.put(dvd.getDVDTitle(), dvd);
        writeDVDCollection();
        return newDVD;
    }

    @Override
    public DVD viewDVDInfo(String dvdTitle)
            throws DVDCollectionDaoException {
        loadDVDCollection();
        return dvds.get(dvdTitle);
    }

    @Override
    public DVD editDVD(String dvdTitle) throws DVDCollectionDaoException {
        loadDVDCollection();
        return dvds.get(dvdTitle);
    }

    @Override
    public void overWriteDVD() throws DVDCollectionDaoException {
        writeDVDCollection();
    }

    @Override
    public DVD removeDVD(String dvdTitle) throws DVDCollectionDaoException {
        loadDVDCollection();
        DVD removedDVD = dvds.remove(dvdTitle);
        writeDVDCollection();
        return removedDVD;
    }
}
