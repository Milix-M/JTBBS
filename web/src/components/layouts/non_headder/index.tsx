import { ReactElement } from "react";

type LayoutProps = Required<{
    readonly children: ReactElement;
}>;

export const Layout = ({ children } : LayoutProps) => (
    <main className="justify-center min-h-screen flex items-center">
        {children}
    </main>
);

const getLayout = (page: React.ReactElement) => <Layout>{page}</Layout>;

export default getLayout;