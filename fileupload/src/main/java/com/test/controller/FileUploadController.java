package com.test.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.model.Directory;

@Controller
public class FileUploadController {

	private static final Log LOG = LogFactory.getLog(FileUploadController.class);

	// The Environment object will be used to read parameters from the
	// application.properties configuration file
	@Autowired
	private Environment env;

	@Autowired
	private ResourceLoader resourceLoader;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/uploadfile")
	public String uploadFileDirectorio(Model model) {

		List<Directory> directories = new ArrayList<Directory>();
		Directory directory = new Directory();
		directory.setCode("111001");
		directory.setName("Name-111001");
		directory.setComments("Comments 1110001");
		directories.add(directory);

		directory = new Directory();
		directory.setCode("111002");
		directory.setName("Name-111002");
		directory.setComments("Comments 1110002");
		directories.add(directory);

		directory = new Directory();
		directory.setCode("111002");
		directory.setName("Name-111002");
		directory.setComments("Comments 1110002");
		directories.add(directory);

		directory = new Directory();
		directory.setCode("111002");
		directory.setName("Name-111002");
		directory.setComments("Comments 1110002");
		directories.add(directory);

		model.addAttribute("directories", directories);

		return "uploadfile";
	}

	/**
	 * POST /uploadFile -> receive and locally save a file.
	 * 
	 * @param uploadfile
	 *            The uploaded file as Multipart file parameter in the HTTP request.
	 *            The RequestParam name must be the same of the attribute "name" in
	 *            the input tag with type file. Aquí se sube directamente y si todo
	 *            ha ido bien puedo abrirlo y hacer lo que quiera con el
	 * 
	 * @return An http OK status in case of success, an http 4xx status in case of
	 *         errors.
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {

		String filename = "";
		String directory = "";
		String uploadFilePath = "";
		try {
			// Get the filename and build the local file path
			filename = uploadfile.getOriginalFilename();
			directory = env.getProperty("upload.file.path");
			uploadFilePath = Paths.get("." + File.separator + directory, filename).toString();
			LOG.info("directory: " + directory);
			LOG.info("uploadFilePath: " + uploadFilePath);

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadFilePath)));
			stream.write(uploadfile.getBytes());
			stream.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			LOG.info("uploadFile method FAILED");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// En este punto tengo el fichero y puedo hacer las operaciones que
		// quiera con el

		LOG.info("uploadFile method SUCCESS");

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
