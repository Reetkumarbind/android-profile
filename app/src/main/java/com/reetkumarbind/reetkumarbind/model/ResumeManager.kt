package com.reetkumarbind.reetkumarbind.model

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

/**
 * Data class representing a project in the portfolio
 */
data class Project(
    val id: String,
    val title: String,
    val description: String,
    val technologies: List<String>,
    val imageUrl: String? = null,
    val projectUrl: String? = null,
    val githubUrl: String? = null,
    val isCompleted: Boolean = true
)

/**
 * Data class representing an experience/milestone in the learning journey
 */
data class Experience(
    val id: String,
    val title: String,
    val description: String,
    val period: String,
    val technologies: List<String> = emptyList(),
    val company: String? = null,
    val isCurrently: Boolean = false
)

/**
 * Data class representing a skill with proficiency level
 */
data class Skill(
    val name: String,
    val category: SkillCategory,
    val proficiency: Float, // 0.0 to 1.0
    val icon: ImageVector? = null
)

/**
 * Enum for skill categories
 */
enum class SkillCategory(val displayName: String) {
    PROGRAMMING_LANGUAGES("Programming Languages"),
    FRAMEWORKS("Frameworks & Libraries"),
    TOOLS("Tools & Technologies"),
    DATABASES("Databases")
}

/**
 * Data class representing educational qualification
 */
data class Education(
    val id: String,
    val degree: String,
    val institution: String,
    val year: String,
    val percentage: String,
    val location: String = ""
)

/**
 * Data class representing contact information
 */
data class ContactInfo(
    val type: ContactType,
    val value: String,
    val displayText: String = value,
    val icon: ImageVector
)

/**
 * Enum for contact types
 */
enum class ContactType {
    EMAIL,
    GITHUB,
    LINKEDIN,
    WEBSITE,
    PHONE
}

/**
 * Manager class for handling resume-related operations and portfolio data
 */
class ResumeManager(private val context: Context) {
    
    companion object {
        const val FULL_NAME = "Reet Kumar Bind"
        const val ROLE = "Software Engineer"
        const val LOCATION = "Delhi, India"
        const val PROFILE_IMAGE_URL = "https://reetkumarbind.netlify.app/reet.JPG"
        const val BIO = "I'm a dedicated Software Engineer with over 9 years of experience in IT support, system administration, and software development. Currently working at Appolo Computers managing our most valuable PSU client  ONGC. For  ONGC DISHA PAPERLESS PROJECT. I have extensive experience in enterprise applications, system integration, and technical support across various domains including government projects, insurance, and e-procurement solutions and more."
        const val TAGLINE = "Building innovative solutions with clean code and modern technologies"
        
        /**
         * Get list of portfolio projects
         */
        fun getProjects(): List<Project> = listOf(
            Project(
                id = "1",
                title = "Portfolio App (Android)",
                description = "Modern portfolio application built with Jetpack Compose showcasing skills, projects, and experience with beautiful UI/UX design.",
                technologies = listOf("Kotlin", "Jetpack Compose", "Material Design 3", "Navigation Component"),
                projectUrl = "https://reetkumar.vercel.app",
                githubUrl = "https://github.com/Reetkumarbind"
            ),
            Project(
                id = "2",
                title = "E-Commerce Platform (Java)",
                description = "Full-featured e-commerce application with user authentication, product catalog, shopping cart, and order management.",
                technologies = listOf("Java", "Spring Boot", "MySQL", "REST API", "Thymeleaf"),
                githubUrl = "https://github.com/Reetkumarbind"
            ),
            Project(
                id = "3",
                title = "Task Management App (React Native)",
                description = "Cross-platform mobile application for task and project management with real-time synchronization.",
                technologies = listOf("React Native", "JavaScript", "AsyncStorage", "Expo"),
                githubUrl = "https://github.com/Reetkumarbind"
            ),
            Project(
                id = "4",
                title = "Weather App (React Native)",
                description = "Real-time weather application with location-based forecasts and beautiful weather animations.",
                technologies = listOf("React Native", "TypeScript", "OpenWeather API", "Geolocation"),
                githubUrl = "https://github.com/Reetkumarbind"
            )
        )
        
        /**
         * Get professional work experiences
         */
        fun getExperiences(): List<Experience> = listOf(
            Experience(
                id = "1",
                title = "Software Engineer",
                description = "Managing ONGC Govt. Client Users application regarding requests. Resolving issues related to DISHA ONGC PAPERLESS PROJECT using HTML, CSS and JavaScript. Managing File handover, File reassignment requests for ONGCIENCs, and Secure File Transfer Protocol Management.",
                period = "Feb 2021 - Present",
                company = "Appolo Computers",
                technologies = listOf("HTML", "CSS", "JavaScript", "SFTP", "Government Applications", "File Management"),
                isCurrently = true
            ),
            Experience(
                id = "2",
                title = "Software Engineer",
                description = "Worked on ONGC Govt. Client Users application management. Handled DISHA ONGC PAPERLESS PROJECT issues and service requests. Managed File handover and reassignment requests for ONGCIENCs with SFTP management.",
                period = "Feb 2021 - July 2023",
                company = "LTI - Tecnics Integration Technology Pvt Ltd (Payroll)",
                technologies = listOf("HTML", "CSS", "JavaScript", "SFTP", "Government Projects", "System Administration")
            ),
            Experience(
                id = "3",
                title = "IT Helpdesk",
                description = "Managed CBIC Govt. Client and IBM Users data requests. Provided 24/7 support through calls, emails & messages. Handled Adobe Applications, VPN creation, IBM Notes issues, printer configuration, scanner support, and Active Directory management.",
                period = "Aug 2018 - Dec 2020",
                company = "IBM - Sampark Software Pvt Ltd (Payroll)",
                technologies = listOf("Active Directory", "ServiceNow", "IBM Notes", "VPN", "Adobe Applications", "MS Outlook", "ITSME/HPSME")
            ),
            Experience(
                id = "4",
                title = "System Engineer",
                description = "Managed Tata Sky Client and Vendor Users. Provided IT support and resolutions for various technical issues including Adobe Applications, VPN, Outlook, printer configuration, and Active Directory services.",
                period = "Aug 2016 - Oct 2018",
                company = "TATA Consultancy Services Bangalore - Foraysoft Pvt Ltd (Payroll)",
                technologies = listOf("Active Directory", "MS Outlook", "ITSME", "System Administration", "Technical Support")
            ),
            Experience(
                id = "5",
                title = "Operation Executive",
                description = "Handled insurance claim requests, monitored CRM applications, tracked claim request lifecycle, and prepared daily work reports. Managed database dump files and prioritized claim processing.",
                period = "Feb 2016 - Aug 2016",
                company = "Medi Assist India TPA Pvt. Ltd Bangalore",
                technologies = listOf("CRM Applications", "Database Management", "Insurance Processing", "Report Generation")
            ),
            Experience(
                id = "6",
                title = "Operation Executive",
                description = "Managed RA Business Rules & Vendor Details for clients (KIOCL & BHEL). Conducted Sealed Auctions and Reverse Auctions, handled pre and post-event documentation, and prepared detailed reports for clients.",
                period = "Mar 2015 - Sep 2015",
                company = "BOB eProcure Solutions Pvt Ltd",
                technologies = listOf("E-Procurement", "Auction Management", "Vendor Management", "Business Process Management")
            )
        )
        
        /**
         * Alias for getExperiences() for backward compatibility
         */
        fun getExperience(): List<Experience> = getExperiences()
        
        /**
         * Get technical skills with proficiency levels
         */
        fun getSkills(): List<Skill> = listOf(
            // Programming Languages
            Skill("Java", SkillCategory.PROGRAMMING_LANGUAGES, 0.9f, Icons.Default.Code),
            Skill("Kotlin", SkillCategory.PROGRAMMING_LANGUAGES, 0.85f, Icons.Default.Code),
            Skill("JavaScript", SkillCategory.PROGRAMMING_LANGUAGES, 0.8f, Icons.Default.Code),
            Skill("TypeScript", SkillCategory.PROGRAMMING_LANGUAGES, 0.75f, Icons.Default.Code),
            Skill("SQL", SkillCategory.PROGRAMMING_LANGUAGES, 0.85f, Icons.Default.Code),
            
            // Frameworks & Libraries
            Skill("Spring Boot", SkillCategory.FRAMEWORKS, 0.9f, Icons.Default.Build),
            Skill("Spring Framework", SkillCategory.FRAMEWORKS, 0.85f, Icons.Default.Build),
            Skill("Jetpack Compose", SkillCategory.FRAMEWORKS, 0.85f, Icons.Default.Build),
            Skill("React Native", SkillCategory.FRAMEWORKS, 0.8f, Icons.Default.Build),
            Skill("REST APIs", SkillCategory.FRAMEWORKS, 0.9f, Icons.Default.Build),
            
            // Tools & Technologies
            Skill("Enterprise Applications", SkillCategory.TOOLS, 0.9f, Icons.Default.Build),
            Skill("System Integration", SkillCategory.TOOLS, 0.85f, Icons.Default.Build),
            Skill("System Administration", SkillCategory.TOOLS, 0.8f, Icons.Default.Build),
            Skill("Network Administration", SkillCategory.TOOLS, 0.75f, Icons.Default.Build),
            Skill("Android Studio", SkillCategory.TOOLS, 0.85f, Icons.Default.Build),
            Skill("Git & GitHub", SkillCategory.TOOLS, 0.8f, Icons.Default.Build),
            Skill("Windows Server", SkillCategory.TOOLS, 0.8f, Icons.Default.Build),
            
            // Databases
            Skill("Oracle", SkillCategory.DATABASES, 0.85f, Icons.Default.Storage),
            Skill("MySQL", SkillCategory.DATABASES, 0.8f, Icons.Default.Storage),
            Skill("SQL", SkillCategory.DATABASES, 0.9f, Icons.Default.Storage),
            Skill("SQLite", SkillCategory.DATABASES, 0.8f, Icons.Default.Storage),
            Skill("MongoDB", SkillCategory.DATABASES, 0.8f, Icons.Default.Storage)
        )
        
        /**
         * Get educational qualifications
         */
        fun getEducation(): List<Education> = listOf(
            Education(
                id = "1",
                degree = "MCA (Master of Computer Applications)",
                institution = "T. John Institute of Technology",
                year = "2013",
                percentage = "61%",
                location = "Bangalore"
            ),
            Education(
                id = "2",
                degree = "BCA (Bachelor of Computer Applications)",
                institution = "IICS",
                year = "2009",
                percentage = "65%",
                location = "Allahabad"
            ),
            Education(
                id = "3",
                degree = "Intermediate",
                institution = "M.G.I.C.",
                year = "2006",
                percentage = "66.5%",
                location = "Allahabad"
            ),
            Education(
                id = "4",
                degree = "High School",
                institution = "Dr. Lohia Intermediate College",
                year = "2004",
                percentage = "64.6%",
                location = "Allahabad"
            )
        )
        
        /**
         * Get personal information
         */
        fun getPersonalInfo(): Map<String, String> = mapOf(
            "Date of Birth" to "January 15th, 1989",
            "Gender" to "Male",
            "Father Name" to "Shitala Prasad Bind",
            "Address" to "Noida, Salarpur Bhangel",
            "Marital Status" to "Married",
            "Languages" to "Hindi, English (Read, Speak, Write)",
            "Nationality" to "Indian"
        )
        
        /**
         * Get academic project information
         */
        fun getAcademicProject(): Map<String, String> = mapOf(
            "Title" to "Cloud Data Production for Masses",
            "Description" to "The objective of my project is to provide security to cloud data production in an efficient manner. It provides strong data protection as service (DPaaS) offering strong data protection to cloud users while enabling rich applications. We explored new cloud platform architecture called data protection as service."
        )
        
        /**
         * Get strengths and key skills
         */
        fun getStrengths(): List<String> = listOf(
            "Strong Work Ethics",
            "Excellent Analytical Capabilities", 
            "Self-Motivated",
            "Team-Work Oriented",
            "Strong Analytical Skills",
            "Positive Attitude towards Learning New Things"
        )

        /**
         * Get contact information
         */
        fun getContactInfo(): List<ContactInfo> = listOf(
            ContactInfo(
                type = ContactType.EMAIL,
                value = "reetkumarbind@gmail.com",
                displayText = "reetkumarbind@gmail.com",
                icon = Icons.Default.Email
            ),
            ContactInfo(
                type = ContactType.GITHUB,
                value = "https://github.com/Reetkumarbind",
                displayText = "github.com/Reetkumarbind",
                icon = Icons.Default.Code
            ),
            ContactInfo(
                type = ContactType.LINKEDIN,
                value = "https://linkedin.com/in/reetkumarbind",
                displayText = "linkedin.com/in/reetkumarbind",
                icon = Icons.Default.Person
            ),
            ContactInfo(
                type = ContactType.WEBSITE,
                value = "https://reetkumarbind.netlify.app",
                displayText = "reetkumarbind.netlify.app",
                icon = Icons.Default.Language
            )
        )
    }
    
    /**
     * Extracts the resume PDF from assets and opens it with a PDF viewer
     */
    fun openResume() {
        try {
            val assetManager = context.assets
            // Using the placeholder text file instead of PDF for this demo
            val inputStream = assetManager.open("resume_placeholder.txt")
            
            // Create a temporary file to store the text
            val tempFile = File(context.cacheDir, "resume_placeholder.txt")
            val outputStream = FileOutputStream(tempFile)
            
            // Copy the text file from assets to the temporary file
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            
            // Get a content URI for the file using FileProvider
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                tempFile
            )
            
            // Create an intent to view the text file
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "text/plain")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            
            // Start the activity to view the text file
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle error - could show a toast or dialog
        }
    }
}