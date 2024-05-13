import { ThemeToggle } from "@/components/theme-toggle";

const Header = () => {
    <header className="flex justify-start bg-overlay border-slate-600">
        <div>
            <ThemeToggle />
        </div>
    </header>
}

export default Header;