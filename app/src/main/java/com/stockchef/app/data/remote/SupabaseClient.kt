package com.stockchef.app.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseManager {

    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://hftcmadqlqymfyusuvau.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhmdGNtYWRxbHF5bWZ5dXN1dmF1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzM2Mzc4MDEsImV4cCI6MjA4OTIxMzgwMX0.sPqiKMxVLiB__hV_L12UlKinfqx9LWEXg8d7n9NEZu8"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}
