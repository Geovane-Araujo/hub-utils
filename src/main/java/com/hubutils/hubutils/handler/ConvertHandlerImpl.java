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
@RequestMapping("utils")
@CrossOrigin(origins = {"http:localhost:8090",""})
public class ConvertHandlerImpl {

    @Autowired
    ConvertController convertController;

    @PostMapping("/actions/convertb64")
    public ResponseEntity<?> convertImageMultipart(@RequestBody Hashtable images){
        String ret = convertController.imageB64(images.get("imgString").toString(),images.get("imgText").toString());
        return ResponseEntity.ok().body(ret);
    }
}
