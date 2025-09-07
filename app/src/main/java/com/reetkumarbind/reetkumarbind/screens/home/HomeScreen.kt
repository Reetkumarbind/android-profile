package com.reetkumarbind.reetkumarbind.screens.home

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage

import com.reetkumarbind.reetkumarbind.model.ResumeManager
import com.reetkumarbind.reetkumarbind.navigation.NavDestination
import com.reetkumarbind.reetkumarbind.ui.theme.ReetKumarBindTheme
import com.reetkumarbind.reetkumarbind.ui.theme.*
import com.reetkumarbind.reetkumarbind.ui.components.ScreenWithBackground
import kotlinx.coroutines.delay

/**
 * Home screen composable that displays the introduction section with enhanced features
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    var isVisible by remember { mutableStateOf(false) }
    
    // Trigger animations on screen load
    LaunchedEffect(Unit) {
        delay(300)
        isVisible = true
    }
    
    ScreenWithBackground {
        HomeContent(
            isVisible = isVisible,
            scrollState = scrollState,
            navController = navController
        )
    }
}


@Composable
private fun HomeContent(
    isVisible: Boolean,
    scrollState: ScrollState,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedSection(
            visible = isVisible,
            delay = 0,
            offsetY = { -it }
        ) {
            HeroSection()
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        AnimatedSection(
            visible = isVisible,
            delay = 400,
            offsetY = { it }
        ) {
            QuickStatsSection()
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        AnimatedSection(
            visible = isVisible,
            delay = 800,
            offsetY = { it }
        ) {
            CallToActionSection(navController = navController)
        }
    }
}

@Composable
private fun AnimatedSection(
    visible: Boolean,
    delay: Int,
    offsetY: (Int) -> Int,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = offsetY,
            animationSpec = tween(800, delayMillis = delay, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(800, delayMillis = delay))
    ) {
        content()
    }
}

@Composable
private fun ProfilePicture() {
    Box(
        modifier = Modifier.size(150.dp),
        contentAlignment = Alignment.Center
    ) {
        // Colorful border
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(
                    brush = androidx.compose.ui.graphics.Brush.sweepGradient(
                        colors = listOf(
                            AccentOrange, AccentYellow, AccentGreen,
                            AccentTeal, AccentPurple, AccentRose, AccentOrange
                        )
                    )
                )
        )
        
        // Profile image
        Card(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            SubcomposeAsyncImage(
                model = "https://reetkumarbind.netlify.app/reet.JPG",
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.size(40.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                error = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile Picture Placeholder",
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            )
        }
    }
}

@Composable
private fun HeroSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        
        ProfilePicture()
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = ResumeManager.FULL_NAME,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            ),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        GradientRoleCard()
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = ResumeManager.TAGLINE,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun GradientRoleCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                        colors = listOf(GradientStart, GradientMiddle, GradientEnd)
                    )
                )
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = ResumeManager.ROLE,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        }
    }
}

@Composable
private fun QuickStatsSection() {
    val projects = ResumeManager.getProjects()
    val skills = ResumeManager.getSkills()
    val experience = ResumeManager.getExperience()
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.linearGradient(
                        colors = listOf(
                            SurfaceTintBlue.copy(alpha = 0.8f),
                            SurfaceTintCyan.copy(alpha = 0.6f),
                            SurfaceTintPink.copy(alpha = 0.4f),
                            SurfaceTintPurple.copy(alpha = 0.6f)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(28.dp)
            ) {
                // Enhanced header with colorful styling
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.9f),
                                    Color.White.copy(alpha = 0.7f)
                                )
                            ),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {
                    // Colorful animated icon
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                brush = androidx.compose.ui.graphics.Brush.radialGradient(
                                    colors = listOf(
                                        AccentPurple,
                                        AccentPurple.copy(alpha = 0.7f)
                                    )
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Portfolio Overview",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        color = GradientStart
                    )
                }
                
                Spacer(modifier = Modifier.height(28.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(
                        icon = Icons.Default.Work,
                        count = projects.size.toString(),
                        label = "Projects",
                        color = AccentOrange
                    )
                    
                    StatItem(
                        icon = Icons.Default.Code,
                        count = skills.size.toString(),
                        label = "Skills",
                        color = AccentPurple
                    )
                    
                    StatItem(
                        icon = Icons.Default.School,
                        count = experience.size.toString(),
                        label = "Experience",
                        color = AccentGreen
                    )
                }
            }
        }
    }
}

@Composable
private fun StatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    count: String,
    label: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Enhanced colorful card with multiple layers
        Box(
            modifier = Modifier.size(64.dp),
            contentAlignment = Alignment.Center
        ) {
            // Outer glow effect
            Card(
                modifier = Modifier.size(64.dp),
                colors = CardDefaults.cardColors(
                    containerColor = color.copy(alpha = 0.2f)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {}
            
            // Main colorful card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.size(60.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.radialGradient(
                                colors = listOf(
                                    color.copy(alpha = 0.9f),
                                    color.copy(alpha = 0.7f),
                                    color.copy(alpha = 0.5f),
                                    color.copy(alpha = 0.3f)
                                ),
                                radius = 80f
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Animated icon with enhanced styling
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Enhanced count with gradient text effect
        Text(
            text = count,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            color = color
        )
        
        // Enhanced label with subtle color
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = color.copy(alpha = 0.8f)
        )
    }
}

@Composable
private fun CallToActionSection(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        // Enhanced title with gradient text effect
        Text(
            text = "Let's Build Something Amazing Together!",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            color = GradientStart,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(
            text = "Explore my projects, experience, and skills. \nReady to collaborate on your next project?",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Enhanced "View Projects" button with gradient
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                                colors = listOf(
                                    AccentOrange,
                                    AccentYellow
                                )
                            )
                        )
                        .clickable {
                            navController.navigate(NavDestination.Projects.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Work,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "View Projects",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White
                        )
                    }
                }
            }
            
            // Enhanced "Contact Me" button with gradient border
            Card(
                modifier = Modifier.weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Gradient border effect
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                                    colors = listOf(
                                        AccentTeal,
                                        AccentPurple
                                    )
                                ),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = RoundedCornerShape(14.dp)
                                )
                                .clickable {
                                    navController.navigate(NavDestination.Contact.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Mail,
                                    contentDescription = null,
                                    tint = AccentTeal,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    "Contact Me",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = AccentTeal
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ReetKumarBindTheme {
        HomeScreen(navController = rememberNavController())
    }
}