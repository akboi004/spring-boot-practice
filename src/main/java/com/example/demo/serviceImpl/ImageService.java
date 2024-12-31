package com.example.demo.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ImageEntity;
import com.example.demo.repo.ImageRepo;

@Service
public class ImageService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	private final ImageRepo imageRepo;

	public ImageService(ImageRepo imageRepo) {
		this.imageRepo = imageRepo;
	}

	public ImageEntity uploadImage(MultipartFile file) throws IOException {

		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdir();
		}

		String filePath = uploadDir + "/" + file.getOriginalFilename();
		Path path = Paths.get(filePath);
		Files.write(path, file.getBytes());

		ImageEntity image = new ImageEntity();
		image.setName(file.getOriginalFilename());
		image.setType(file.getContentType());
		image.setData(filePath);
		return imageRepo.save(image);
	}

	// Fetch Image Metadata by ID
	public ImageEntity getImageMetadata(Long id) throws Exception {
		Optional<ImageEntity> optionalImage = imageRepo.findById(id);
		if (optionalImage.isPresent()) {
			return optionalImage.get(); // Return image metadata (including file path)
		} else {
			throw new Exception("Image not found with ID: " + id);
		}
	}

	// Fetch Image by ID
	public byte[] getImage(Long id) throws Exception {
		ImageEntity image = getImageMetadata(id); // Get metadata
		Path path = Paths.get(image.getData()); // File path from metadata
		return Files.readAllBytes(path);
	}

	public void deleteImage(Long id) throws Exception {
		Optional<ImageEntity> image = imageRepo.findById(id);
		if (image.isPresent()) {
			Path path = Paths.get(image.get().getData());
			Files.deleteIfExists(path);

			imageRepo.deleteById(id);
		} else {
			throw new Exception("Image not found with ID: " + id);
		}
	}

}
