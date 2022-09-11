package com.hubutils.hubutils.handler;

import com.hubutils.hubutils.controller.ConvertController;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("hub/util")
@CrossOrigin("*")
public class ConvertHandlerImpl {

    @Autowired
    ConvertController convertController;

    @PostMapping("/convertImage")
    public ResponseEntity<?> convertImage(@RequestBody(required = true) Map<String,Object> body){
        try {
            HtmlConverter.convertToPdf(body.get("body").toString(),new FileOutputStream("string.pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body("c");
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertImageMultipart(@RequestBody MultipartFile multipartRequest){
        convertController.image(multipartRequest);
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/convertb64")
    public ResponseEntity<?> convertImageMultipart(@RequestBody Hashtable images){
        String ret = convertController.imageB64(images.get("imgString").toString(),images.get("imgText").toString());
        return ResponseEntity.ok().body(ret);
    }
}
