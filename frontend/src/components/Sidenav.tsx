import { Home, IndianRupee, SidebarIcon, UsersRound, WalletMinimal } from "lucide-react";
import { Sidebar, SidebarContent, SidebarFooter, SidebarGroup, SidebarGroupLabel, SidebarHeader, SidebarMenu, SidebarMenuButton, SidebarMenuItem } from "./ui/sidebar";

const menu = [
    {
        title: "Dashboard",
        url: "/dashboard",
        icon: Home
    },
    {
        title: "Group",
        url: "/group",
        icon: UsersRound
    },
    {
        title: "Expense",
        url: "/expense",
        icon: IndianRupee
    },
    {
        title: "Settle",
        url: "/settlement",
        icon: WalletMinimal
    }
]

export default function Sidenav() {
    return (
        <Sidebar>
            <SidebarHeader>
                <span>
                    S0ggy
                </span>
            </SidebarHeader>
            <SidebarContent>
                <SidebarGroup>
                    <SidebarGroupLabel> 
                        Utility
                    </SidebarGroupLabel>
                    <SidebarMenu>
                        {menu.map(item => (
                            <SidebarMenuItem key={item.title}>
                                <SidebarMenuButton asChild>
                                    <a href={item.url}>
                                        <item.icon />
                                        <span>{item.title}</span>
                                    </a>
                                </SidebarMenuButton>
                            </SidebarMenuItem>
                        ))}
                    </SidebarMenu>
                </SidebarGroup>
            </SidebarContent>
            <SidebarFooter>
                Footer
            </SidebarFooter>
        </Sidebar>
    );
}