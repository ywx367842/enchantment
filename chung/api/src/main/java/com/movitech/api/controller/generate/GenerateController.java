package com.movitech.api.controller.generate;

import com.movitech.api.util.common.Response;
import com.movitech.api.util.generate.CodeGenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping(value = "/")
public class GenerateController {

    @Autowired
    private CodeGenerateUtils codeGenerateUtils;

    @PostMapping("/generate")
    public Response generate() {
        try {
            codeGenerateUtils.generate();
            codeGenerateUtils.getCurrentDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.succeed("代码生成成功");
    }

}
