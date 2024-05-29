package hello.borad.upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/spring")
@Controller
public class SpringUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping( value = "/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV3(@RequestParam("itemName") String itemName,
                             @RequestParam("file") MultipartFile file,
                             HttpServletRequest request
                             ) throws ServletException, IOException {
        log.info("request = {}", request);
        log.info("file = {}", file);
        log.info("itemNmae = {}", itemName);

        if(!file.isEmpty()){
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("fullPath = {}", fullPath);
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }


}
