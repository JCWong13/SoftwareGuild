/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.ui;

import com.sg.dvdcollection.dto.DVD;
import java.util.List;

/**
 *
 * @author janie
 */
public class DVDCollectionView {

    UserIO io;

    public DVDCollectionView(UserIO io) {
        this.io = io;
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
        
    }

    public void getAllDVDs(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print(currentDVD.getDVDTitle() + " <> "
                    + currentDVD.getReleaseDate() + " <> "
                    + currentDVD.getMpaaRating() + " <> "
                    + currentDVD.getDirectorName() + " <> "
                    + currentDVD.getStudio() + " <> "
                    + currentDVD.getUserNote());
        }
        io.readString("Please hit enter to continue.");
    }

    public void searchDVDsByTitle(List<DVD> dvdList, String keyword) {
        if (keyword != null) {
            for (DVD currentDVD : dvdList) {
                if (currentDVD.getDVDTitle().contains(keyword)) {
                    io.print(currentDVD.getDVDTitle() + " <> "
                            + currentDVD.getReleaseDate() + " <> "
                            + currentDVD.getMpaaRating() + " <> "
                            + currentDVD.getDirectorName() + " <> "
                            + currentDVD.getStudio() + " <> "
                            + currentDVD.getUserNote());
                }
            }

        } else {
            io.print("No such DVD was found.");

            io.readString("Please hit enter to continue.");
        }
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public DVD getDVDTitle() {
        String dvdTitle = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter DVD release date");
        String mpaaRating = io.readString("Please enter DVD MPAA Rating");
        String directorName = io.readString("Please enter DVD Director Name");
        String studio = io.readString("Please enter DVD studio");
        String userNote = io.readString("Please enter a DVD rating or note");
        DVD currentDVD = new DVD(dvdTitle);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserNote(userNote);
        return currentDVD;
    }
    public String switchReleaseDate() {
    return io.readString("Please enter new Release Date:");
    }
    
    public String switchMpaaRating() {
        return io.readString("Please enter new MPAA Rating:");
    }
    
    public String switchDirectorName() {
        return io.readString("Please enter new Director Name:");
    }
    
    public String switchStudio() {
        return io.readString("Please enter new studio information:");
    }
    
    public String switchUserNote() {
        return io.readString("Please enter new User Rating or Note:");
    }
    public void switchDefault() {
        System.out.println("Please pick a number 1-5.");
    }
    
    public void displayAddDVDBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayAddDVDSuccessBanner() {
        io.readString("DVD successfully added.  Please hit enter to continue");
    }

    public void displayDisplayDVDInformationBanner() {
        io.print("=== Display DVD Information ===");
    }

    public String getdvdTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }
    
    public String getDVDSearchWord(){
        return io.readString("Please enter a keyword to search your DVD Collection");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveDVDSuccessBanner() {
        io.readString("DVD successfully removed.  Please hit enter to continue.");
    }

    public void displayEditDVDBanner() {
        io.print("===Edit DVD Information===");
    }

    public void displayEditDVDSuccessBanner() {
        io.readString("DVD Information successfully updated.  Please hit enter to continue.");
    }

    public void displaySearchDVDTitles() {
        io.print("=== Search DVD Collection ===");
    }

    public void displaySearchEndDVDTitles() {
        io.readString("Entire DVD collection searched.  Please hit enter to continue.");
    }

    public int displaydvdTitleEdit(DVD dvd) {
        if (dvd != null) {
            io.print("DVD Title: " + dvd.getDVDTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director Name: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating/Note: " + dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such DVD was found.");
        }

        int choice = Integer.parseInt(io.readString("Which information would you like to change?"
                + "\nPlease pick a number 1-5."
                + "\n1. Release Date"
                + "\n2. MPAA Rating"
                + "\n3. Director Name"
                + "\n4. Studio"
                + "\n5. User Rating/Note"));
        return choice;

    }
    
    public String askUserEditChoice () {
        return io.readString("Would you like to edit any other information?");
    }

    public void displaydvdTitle(DVD dvd) {
        if (dvd != null) {
            io.print("DVD Title: " + dvd.getDVDTitle());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director Name: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating/Note: " + dvd.getUserNote());
            io.print("");
        } else {
            io.print("No such DVD was found.");
        }
        io.readString("Please hit enter to continue.");
    }

    public int printMenuAndGetSelection() {
        io.print("=== * Main Menu * ===");
        io.print("1. View all DVDs in Collection");
        io.print("2. Search DVD by title and View DVD's Information");
        io.print("3. Search DVD Collection By Title");
        io.print("4. Add New DVD to Collection");
        io.print("5. Edit DVD Information");
        io.print("6. Remove DVD From Collection");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);

    }

    public void displayExitBanner() {
        io.print("=== * GoodBye! * ===");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command -_- Please Try Again");
    }

}
