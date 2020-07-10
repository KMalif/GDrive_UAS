/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDriveUAS;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import java.io.IOException;

/**
 *
 * @author ACER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Drive driveService = GoogleDriveUtils.getDriveService();
        
        File fileMetadata = new File();
        fileMetadata.setName("Invoices");
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        File file = driveService.files().create(fileMetadata)
            .setFields("id")
            .execute();
        System.out.println("Folder ID: " + file.getId());
    }
    
}
