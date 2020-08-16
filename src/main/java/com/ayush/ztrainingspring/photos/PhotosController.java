package com.ayush.ztrainingspring.photos;

import com.ayush.ztrainingspring.order.restaurants.Restaurantrepo;
import com.ayush.ztrainingspring.photos.PhotosService;
import com.ayush.ztrainingspring.reviews.service.ReviewService;
import com.ayush.ztrainingspring.user_auth.User;
import com.ayush.ztrainingspring.order.restaurants.Restaurants;
import com.ayush.ztrainingspring.user_auth.UserRepository;
import com.ayush.ztrainingspring.user_auth.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/photos") // This means URL's start with /photos (after Application path)
public class PhotosController {

    @Autowired
    private PhotosService photosService;

    // private static String UPLOADED_FOLDER = "src/main/resources/photos_storage/";


    @GetMapping(path="/test")
    public String getTemp() {
        // This returns a JSON or XML with the users
        return "ss";
    }


    @GetMapping("/{restId}/all")
    public ResponseEntity getAllPhotos(@PathVariable int restId) {
        List<Photo> photosList =  photosService.getAllPhotosServ(restId);

//        System.out.println("inside getCatPhotos--------------------");


        if(photosList != null)
        {
            return ResponseEntity.ok(photosList);
        }
        else
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No Image Found with restaurant ID:" + restId);
        }
    }

    @GetMapping(path="/{restId}/{category}")
    public ResponseEntity getCatPhotos(@PathVariable int restId,
                                       @PathVariable String category) {
        System.out.println("inside getCatPhotos--------------------");
        List<Photo> photosList =  photosService.getCatPhotosServ(restId, category);

        if(photosList != null)
        {
            return ResponseEntity.ok(photosList);
        }
        else
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("No Image Found with restaurant ID:" + restId + " and category:" + category);
        }
    }

    @GetMapping(path="/userLikes/{id}/{colName}")
    public ResponseEntity updateLikesDislikes(@PathVariable int id,
                                              @PathVariable String colName) {

        Photo photo =  photosService.updateLikesDislikesServ(id, colName);
        if(photo != null)
        {
            return ResponseEntity.ok(photo);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Could not update " + colName + " for photo id:" + id);
        }
    }

    @GetMapping(path="/userDetails/{userID}")
    public ResponseEntity getUserDetails(@PathVariable int userID) {
        // This returns a JSON or XML with the users
        User user = photosService.getUserDetailsServ(userID);
        if(user == null)
        {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User not Found");
        }
        return ResponseEntity.ok(user);
    }



////    @PostMapping("/updateLikes") // //new annotation since 4.3
////    public ResponseEntity updateLikes(@RequestParam("userID") int userID
////                                           @RequestParam("likes") int likes,
////                                           @RequestParam("dislikes") int dislikes) {
////
////    }
//
//
//
    @PostMapping("/upload")
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file,
                                           @RequestParam("category") String category,
                                           @RequestParam("restID") int restID,
                                           @RequestParam("userID") int userID) {

        String fileDownloadUri = photosService.singleFileUploadServ(file, category, restID, userID);

        if (fileDownloadUri.length() == 0) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Could not upload image");
        }

        return ResponseEntity.ok(fileDownloadUri);
    }

    @GetMapping(
      value = "/get-image/{imgName}",
      produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity getImageWithMediaType(@PathVariable String imgName){

        Resource resource = photosService.getImageWithMediaTypeServ(imgName);

        if(resource == null){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Image not Found");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
}
