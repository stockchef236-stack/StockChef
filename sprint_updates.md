Sprint 1 – 2026-03-08

User Story:

As a user, I want to securely log in and access the app so that I can manage my inventory.
Work Done:
Initialized Android project with Jetpack Compose and Material 3
Configured Gradle (Kotlin DSL, version catalogs, dependencies)
Implemented Splash Screen with session-based navigation
Integrated Supabase (Auth, Postgrest, Storage)
Built authentication system (Login/Register UI + ViewModel)
Implemented navigation graph (Splash, Auth, Home)

Outcome:

App launches successfully with splash screen
Users can register, login, and logout
Navigation between screens works correctly


Sprint 2 – 2026-03-12

User Story:

As a user, I want to view and search my inventory items so that I can easily find what I need.
Work Done:
Created Ingredient model and repository interface
Integrated Supabase CRUD operations for ingredients
Implemented DTOs and mappers for data transformation
Built HomeViewModel with StateFlow and search functionality
Designed UI with LazyColumn, search bar, and item cards
Connected ViewModel with navigation and UI

Outcome:

Ingredient list is fetched and displayed
Users can search and filter items
UI updates reactively with data changes


Sprint 3 – 2026-03-16

User Story:

As a user, I want to add, edit, and delete items (with images) so that I can manage my inventory effectively.
Work Done:
Built Add/Edit screen with ViewModel and UI state
Implemented create, update, and delete operations
Added camera integration for capturing item images
Configured FileProvider and permissions
Integrated Supabase Storage for image uploads
Added delete confirmation dialog and improved UI

Outcome:

Users can add new ingredients with images
Users can edit and delete existing items
Images are captured, uploaded, and displayed correctly


Sprint 4 – 2026-03-21

User Story:

As a user, I want my inventory to work offline and update in real-time so that I always have access to my data.
Work Done:
Implemented Room database (Entity, DAO, Database)
Added data mapping between local and domain models
Built caching strategy (local + remote sync)
Implemented network monitoring
Added Settings screen with logout & data clearing
Integrated notifications for low stock alerts

Outcome:

App works offline with cached data
Data syncs automatically when back online
Users receive notifications for low stock items
Users can manage settings and logout






