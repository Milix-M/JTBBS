import { ThemeToggle } from "@/components/theme-toggle";
import Link from "next/link";
import { ReactElement } from "react";
// import Header from "./Header/Header";

type LayoutProps = Required<{
  readonly children: ReactElement;
}>;

export const Layout = ({ children }: LayoutProps) => (
  <>
    <div className="flex">
      <header className="border-r min-h-svh border-slate-600 max-w-xs w-full">
        <div className="p-4">
          <h1 className="text-4xl italic font-bold text-center tracking-widest">
            JTBBS
          </h1>
          <div className="space-y-3 mt-5">
            <div className="border-b p-2">
              <Link href={"/"}>
                <p className="text-center">Home</p>
              </Link>
            </div>
            <p className="text-center mb-2">ボード一覧</p>
            <div>
              <p className="text-center">タイトル</p>
              <p className="text-center">タイトル</p>
            </div>
          </div>
          <div className="">
            <ThemeToggle />
          </div>
        </div>
      </header>
      <main className="w-full mx-auto my-2">{children}</main>
    </div>
  </>
);

const getLayout = (page: React.ReactElement) => <Layout>{page}</Layout>;

export default getLayout;
