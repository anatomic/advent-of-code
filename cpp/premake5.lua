workspace "AOC"
    architecture "ARM64"
    configurations { "Debug", "Release" }
    location "build"

OutputDir = "%{cfg.system}-%{cfg.architecture}/%{cfg.buildcfg}"


project "Core"
   kind "StaticLib"
   language "C++"
   cppdialect "C++20"
   targetdir "bin/%{cfg.buildcfg}"
   staticruntime "off"

   files { "lib/**.h", "lib/**.cpp" }

   includedirs {
      "lib/**"
   }

   links {
        "libcurl"
   }

   targetdir ("bin/" .. OutputDir .. "/%{prj.name}")
   objdir ("bin/intermediates/" .. OutputDir .. "/%{prj.name}")

   filter "configurations:Debug"
       defines { "DEBUG" }
       runtime "Debug"
       symbols "On"

   filter "configurations:Release"
       defines { "RELEASE" }
       runtime "Release"
       optimize "On"
       symbols "On"

project "AOC"
    kind "ConsoleApp"
    language "C++"
    cppdialect "C++20"
    -- targetdir "bin/%{cfg.buildcfg}"
    targetdir ("bin/" .. OutputDir .. "/%{prj.name}")
    objdir ("bin/intermediates/" .. OutputDir .. "/%{prj.name}")
    staticruntime "off"

    files { "**.hpp", "**.cpp" }

    includedirs {
        "",
        "2024",
        -- Include Core
        "lib/**"
    }

    links {
        "Core"
    }

    filter "configurations:Debug"
       defines { "DEBUG" }
       runtime "Debug"
       symbols "On"

    filter "configurations:Release"
       defines { "NDEBUG" }
       runtime "Release"
       optimize "On"
