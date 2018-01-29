/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.Controller;

import com.sg.dvdcollection.dao.DVDCollectionDao;
import com.sg.dvdcollection.dao.DVDCollectionDaoException;
import com.sg.dvdcollection.dto.DVD;
import com.sg.dvdcollection.ui.DVDCollectionView;
import java.util.List;

/**
 *
 * @author janie
 */
public class DVDCollectionController {

    DVDCollectionDao dao;
    DVDCollectionView view;

    public DVDCollectionController(DVDCollectionDao dao, DVDCollectionView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;

        try {
            while (keepGoing) {

                int menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        getAllDVDs();
                        break;
                    case 2:
                        viewDVDInformation();
                        break;
                    case 3:
                        searchDVDsByTitle();
                        break;
                    case 4:
                        addDVD();
                        break;
                    case 5:
                        editDVDInformation();
                        break;
                    case 6:
                        removeDVD();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }

            exitMessage();
        } catch (DVDCollectionDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void getAllDVDs() throws DVDCollectionDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.getAllDVDs(dvdList);
    }

    private void addDVD() throws DVDCollectionDaoException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getDVDTitle();
        dao.addDVD(newDVD);
        view.displayAddDVDSuccessBanner();

    }

    private void searchDVDsByTitle() throws DVDCollectionDaoException {
        view.displaySearchDVDTitles();
        String dvdTitle = view.getDVDSearchWord();
        view.searchDVDsByTitle(dao.getAllDVDs(), dvdTitle);
        view.displaySearchEndDVDTitles();
    }

    private void viewDVDInformation() throws DVDCollectionDaoException {
        view.displayDisplayDVDInformationBanner();
        String dvdTitle = view.getdvdTitleChoice();
        DVD dvd = dao.viewDVDInfo(dvdTitle);
        view.displaydvdTitle(dvd);
    }

    public void editDVDInformation() throws DVDCollectionDaoException {
        view.displayEditDVDBanner();
        String dvdTitle = view.getdvdTitleChoice();
        DVD dvd = dao.editDVD(dvdTitle);

        do {
            int choice = view.displaydvdTitleEdit(dvd);

            switch (choice) {
                case 1:
                    String releaseDate = view.switchReleaseDate();
                    dvd.setReleaseDate(releaseDate);
                    break;
                case 2:
                    String mPaaRating = view.switchMpaaRating();
                    dvd.setMpaaRating(mPaaRating);
                    break;
                case 3:
                    String directorName = view.switchDirectorName();
                    dvd.setDirectorName(directorName);
                    break;
                case 4:
                    String studio = view.switchStudio();
                    dvd.setStudio(studio);
                    break;
                case 5:
                    String userRating = view.switchUserNote();
                    dvd.setUserNote(userRating);
                    break;
                default:
                    view.switchDefault();
            }
        } while (view.askUserEditChoice().equalsIgnoreCase("Yes"));
        dao.overWriteDVD();
        view.displayEditDVDSuccessBanner();
    }

    private void removeDVD() throws DVDCollectionDaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getdvdTitleChoice();
        dao.removeDVD(dvdTitle);
        view.displayRemoveDVDSuccessBanner();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
