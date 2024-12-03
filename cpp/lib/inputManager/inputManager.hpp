#include <string>
#include <filesystem>

namespace AOC {
  class InputManager {
    public:
      static std::string getInput(int year, int day);

    private:
      // static std::string downloadInput(int year, int day);
      // static std::string getCachePath(int year, int day);
      // static bool saveToCache(const std::string& path, const std::string& input);
      // static std::string getSessionToken();
  };
}
