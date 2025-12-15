package edu.icet.clothify.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CloudinaryUtil {
    private static Cloudinary cloudinary;

    static {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dnv1qojla",
                "api_key", "392232224942483",
                "api_secret", "M2-Bh7vZ9qe9qQmuQWT1S9ouoK0"
        ));
    }

    public static String uploadImage(File file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return (String) uploadResult.get("url");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
