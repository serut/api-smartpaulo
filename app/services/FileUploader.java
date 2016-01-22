package services;

/**
 * Created by Leo on 15/01/16.
 */
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider ;
import com.amazonaws.services.s3.model.CannedAccessControlList;


public class FileUploader{
    public static String uploadMediaFile(InputStream media) throws Exception{
        Calendar calendar = Calendar.getInstance();

        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentType("image/bmp");
        omd.setContentLength(media.available());
        String filename = calendar.getTimeInMillis() + ".bmp";
        omd.setHeader("filename", filename);

        AmazonS3 s3client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());
        PutObjectRequest putObj= new PutObjectRequest("m2dl-api-smartpaulo", filename, media, omd);
        putObj.setCannedAcl(CannedAccessControlList.PublicRead);
        s3client.putObject(putObj);
        return("https://s3-eu-west-1.amazonaws.com/m2dl-api-smartpaulo/"+calendar.getTimeInMillis());
    }
}