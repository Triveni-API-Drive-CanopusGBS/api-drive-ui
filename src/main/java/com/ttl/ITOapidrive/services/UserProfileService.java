package com.ttl.ITOapidrive.services;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ttl.ITOapidrive.Exceptions.ResourceNotFoundException;
import com.ttl.ITOapidrive.entities.UserProfile;
import com.ttl.ITOapidrive.repositories.UserProfileRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
    
    public List<UserProfile> getUserProfilesByEmailIdAndUserId(String emailId, Long userId) {
        return userProfileRepository.findByEmailIdAndUserId(emailId, userId);
    }

    public UserProfile updateUserProfile(Long userId, UserProfile userProfile) {
        Optional<UserProfile> existingUserProfile = userProfileRepository.findById(userId);
        if (existingUserProfile.isPresent()) {
            userProfile.setUserId(userId);
            return userProfileRepository.save(userProfile);
        } else {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }
    
    public void deleteUserProfile(Long userId) {
        userProfileRepository.deleteById(userId);
    }

    public List<UserProfile> getAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    public void createUsersFromExcel(MultipartFile file) {
        try {
            List<UserProfile> userProfiles = parseExcelFile(file);
            userProfileRepository.saveAll(userProfiles);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }

    private List<UserProfile> parseExcelFile(MultipartFile file) throws IOException {
        List<UserProfile> userProfiles = new ArrayList<>();
        Workbook workbook = null;
        try {
            String fileName = file.getOriginalFilename();
            if (fileName != null && fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (fileName != null && fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else {
                throw new RuntimeException("Invalid file format. Please upload an Excel file.");
            }

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }

                UserProfile user = new UserProfile();
                user.setEmployeeId(Double.parseDouble(getCellValueAsString(row.getCell(0))));
                user.setEmployeeName(getCellValueAsString(row.getCell(1)));
                user.setEmailId(getCellValueAsString(row.getCell(2)));
                user.setContactNumber(Double.parseDouble(getCellValueAsString(row.getCell(3))));
                user.setGroupId(Double.parseDouble(getCellValueAsString(row.getCell(4))));
                //user.setImage(getCellValueAsString(row.getCell(5)));
                user.setModifiedById(Double.parseDouble(getCellValueAsString(row.getCell(6))));
                user.setDepartment(getCellValueAsString(row.getCell(7)));
                user.setDesignation(getCellValueAsString(row.getCell(8)));
                user.setActiveStatus(Boolean.parseBoolean(getCellValueAsString(row.getCell(9))));

                // Handling roleNames and regionNames as List<String>
                String roleNamesString = getCellValueAsString(row.getCell(10));
                List<String> roleNames = Arrays.stream(roleNamesString.split(","))
                                               .map(String::trim)
                                               .collect(Collectors.toList());
                user.setRoleNames(roleNames);

                String regionNamesString = getCellValueAsString(row.getCell(11));
                List<String> regionNames = Arrays.stream(regionNamesString.split(","))
                                                 .map(String::trim)
                                                 .collect(Collectors.toList());
                user.setRegionNames(regionNames);

                userProfiles.add(user);
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return userProfiles;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        return cell.getRichStringCellValue().getString();
                    case NUMERIC:
                        return String.valueOf(cell.getNumericCellValue());
                    case BOOLEAN:
                        return String.valueOf(cell.getBooleanCellValue());
                    default:
                        return "";
                }
            default:
                return "";
        }
    }
    
}
