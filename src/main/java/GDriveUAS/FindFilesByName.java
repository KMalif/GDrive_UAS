/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GDriveUAS;

/**
 *
 * @author ACER
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import GDriveUAS.GoogleDriveUtils;
 
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
 
public class FindFilesByName {
 
    // com.google.api.services.drive.model.File
    public static final List<File> getGoogleFilesByName(String fileNameLike) throws IOException {
 
        Drive driveService = GoogleDriveUtils.getDriveService();
 
        String pageToken = null;
        List<File> list = new ArrayList<File>();
 
        String query = " name contains '" + fileNameLike + "' " //
                + " and mimeType != 'application/vnd.google-apps.folder' ";
 
        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime, mimeType
                    .setFields("nextPageToken, files(id, name, createdTime, mimeType)")//
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return list;
    }
 
    public static void main(String[] args) throws IOException {
 
        List<File> rootGoogleFolders = getGoogleFilesByName("guide");
        for (File folder : rootGoogleFolders) {
 
            System.out.println("Mime Type: " + folder.getMimeType() + " --- Name: " + folder.getName() +" ID = "+ folder.getId());
        }
//        String fileId = "1E8j2rrB66QH_HzbDXuclXp-JsV_FtrP5";
//        OutputStream outputStream = new ByteArrayOutputStream();
//        GDriveUAS.GoogleDriveUtils.getDriveService().files().get(fileId)
//            .executeMediaAndDownloadTo(outputStream);
        System.out.println("Done!");
    }
}