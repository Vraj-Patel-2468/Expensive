import Aurora from "@/animation/Herobackground";
import { Button } from "@/components/ui/button";

export default function LandingPage() {
  return (
    <>
      <header className="w-full fixed top-0 left-0 z-50 bg-black/80 backdrop-blur-xl border-b border-green-500/20">
        <nav className="max-w-7xl mx-auto flex items-center justify-between px-6 md:px-8 py-4">
          <div className="flex items-center">
            <span className="text-2xl font-bold text-green-400 tracking-wide">
              S0ggy
            </span>
          </div>
          <div className="flex gap-4">
            <Button 
              variant="outline"
              className="px-6 border-green-500 text-green-400 hover:bg-green-500/10 hover:text-green-300"
            >
              Login
            </Button>
            <Button className="px-6 bg-green-500 hover:bg-green-600 text-black font-semibold">
              Sign In
            </Button>
          </div>
        </nav>
      </header>

      <main className="flex flex-col justify-center bg-gradient-to-b from-black via-gray-900 to-black pt-20 px-4 min-h-screen">
        <section className="relative w-full max-w-7xl mx-auto py-20 px-6 flex flex-col items-center text-center overflow-hidden rounded-3xl shadow-2xl bg-black/40 backdrop-blur-lg border border-green-500/30">
          <div className="absolute inset-0 w-full h-full pointer-events-none z-0">
            <Aurora
              colorStops={["#00d8ff", "#7cff67", "#00d8ff"]}
              amplitude={1.0}
              blend={0.5}
            />
          </div>
          <div className="relative z-10 max-w-3xl">
            <h1 className="text-4xl md:text-6xl font-extrabold text-white tracking-tight leading-tight mb-6 drop-shadow-lg">
              Split expenses with friends made simple
            </h1>
            <p className="text-lg md:text-2xl text-gray-200 mb-10 font-medium">
              Track group expenses, split bills fairly, and settle up easily. No
              more awkward conversations or forgotten IOUs.
            </p>
            <Button className="bg-green-500 text-black hover:bg-green-400 transition-all duration-300 px-8 py-4 text-lg font-semibold rounded-xl shadow-lg hover:shadow-green-500/20">
              Get Started
            </Button>
          </div>
        </section>

        <section className="w-full max-w-7xl mx-auto mt-16 flex flex-col items-center">
          <div className="mb-12 text-center">
            <h2 className="text-3xl md:text-4xl font-bold text-white mb-4">
              Everything you need to manage group expenses
            </h2>
            <p className="text-lg text-gray-400">
              Simple, transparent, and fair expense splitting for any group
            </p>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8 w-full">
            <div className="bg-gray-900/80 rounded-2xl shadow-xl p-8 flex flex-col items-center border border-gray-800 hover:border-green-500/50 transition-all duration-300 hover:shadow-green-500/10">
              <div className="w-16 h-16 bg-green-500/20 rounded-full flex items-center justify-center mb-4">
                <div className="w-8 h-8 bg-green-500 rounded-full"></div>
              </div>
              <span className="text-xl font-semibold mb-3 text-white">
                Group Management
              </span>
              <p className="text-gray-400 text-center leading-relaxed">
                Create groups and invite friends to track shared expenses
                effortlessly
              </p>
            </div>
            <div className="bg-gray-900/80 rounded-2xl shadow-xl p-8 flex flex-col items-center border border-gray-800 hover:border-green-500/50 transition-all duration-300 hover:shadow-green-500/10">
              <div className="w-16 h-16 bg-green-500/20 rounded-full flex items-center justify-center mb-4">
                <div className="w-8 h-8 bg-green-500 rounded-full"></div>
              </div>
              <span className="text-xl font-semibold mb-3 text-white">
                Smart Splitting
              </span>
              <p className="text-gray-400 text-center leading-relaxed">
                Automatically calculate who owes what with intelligent expense
                splitting
              </p>
            </div>
            <div className="bg-gray-900/80 rounded-2xl shadow-xl p-8 flex flex-col items-center border border-gray-800 hover:border-green-500/50 transition-all duration-300 hover:shadow-green-500/10">
              <div className="w-16 h-16 bg-green-500/20 rounded-full flex items-center justify-center mb-4">
                <div className="w-8 h-8 bg-green-500 rounded-full"></div>
              </div>
              <span className="text-xl font-semibold mb-3 text-white">
                Easy Settlements
              </span>
              <p className="text-gray-400 text-center leading-relaxed">
                Settle up with friends and track all your payments in one place
              </p>
            </div>
          </div>
        </section>

        <section className="w-full max-w-7xl mx-auto mt-20">
          <div className="bg-gradient-to-r from-gray-900 to-black rounded-3xl shadow-2xl p-12 flex flex-col items-center text-center border border-green-500/30 relative overflow-hidden">
            <div className="absolute inset-0 bg-gradient-to-r from-green-500/5 to-transparent"></div>
            <div className="relative z-10">
              <h3 className="text-3xl md:text-4xl font-bold text-white mb-6">
                Ready to simplify your group expenses?
              </h3>
              <p className="text-lg text-gray-300 mb-8 max-w-2xl">
                Join thousands of users who trust S0ggy for fair expense splitting
              </p>
              <Button className="px-10 py-4 text-lg font-semibold bg-green-500 hover:bg-green-400 text-black transition-all duration-300 rounded-xl shadow-lg hover:shadow-green-500/30">
                Start Splitting
              </Button>
            </div>
          </div>
        </section>
      </main>

      <footer className="w-full bg-black border-t border-gray-800 text-gray-300 py-12 mt-16">
        <div className="max-w-7xl mx-auto px-6 flex flex-col md:flex-row justify-between items-center">
          <div className="mb-6 md:mb-0 flex flex-col items-center md:items-start">
            <span className="text-2xl font-bold text-green-400 mb-2">S0ggy</span>
            <span className="text-sm text-gray-500">
              © {new Date().getFullYear()} S0ggy. All rights reserved.
            </span>
          </div>
          <div className="flex flex-col items-center md:items-end gap-3">
            <div className="flex gap-6 mb-4 md:mb-0">
              <a
                href="https://github.com/vrajpatel/S0ggy"
                target="_blank"
                rel="noopener noreferrer"
                className="hover:text-green-400 transition-colors duration-300"
              >
                GitHub
              </a>
              <a
                href="mailto:vrajpatel@example.com"
                className="hover:text-green-400 transition-colors duration-300"
              >
                Contact
              </a>
            </div>
            <span className="text-sm text-gray-400">Made by Vraj Patel</span>
            <span className="text-xs text-gray-500 mt-1 italic">
              "Splitting bills, uniting friends."
            </span>
          </div>
        </div>
      </footer>
    </>
  );
}