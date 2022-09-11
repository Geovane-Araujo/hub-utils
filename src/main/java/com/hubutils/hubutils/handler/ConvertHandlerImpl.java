package com.hubutils.hubutils.handler;

import com.hubutils.hubutils.controller.ConvertController;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Hashtable;

@RestController
@RequestMapping("utils")
@CrossOrigin(origins = {"http:localhost:8090","*"})
public class ConvertHandlerImpl {

    @Autowired
    ConvertController convertController;

    @PostMapping("/textImage")
    public ResponseEntity<?> textImage(@RequestBody Hashtable images){
        String ret = convertController.imageB64(images.get("imgString").toString(),images.get("imgText").toString());
        return ResponseEntity.ok().body(ret);
    }
}
