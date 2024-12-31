package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ImageEntity;
import com.example.demo.serviceImpl.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			ImageEntity image = imageService.uploadImage(file);
			return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully: ID = " + image.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error uploading image: " + e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Resource> getImage(@PathVariable Long id) throws Exception {
		try {
			ImageEntity image = imageService.getImageMetadata(id); // Get image metadata first
			Path filePath = Paths.get(image.getData());
			Resource resource = new FileSystemResource(filePath.toFile());

			if (!resource.exists()) {
				throw new IOException("File not found: " + filePath);
			}

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
					.contentType(MediaType.IMAGE_JPEG) // Dynamically set the content type based on image format
					.body(resource);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteImage(@PathVariable Long id) {
		try {
			imageService.deleteImage(id);
			return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting image: " + e.getMessage());
		}
	}
}
