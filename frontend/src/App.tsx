import Sidenav from './components/Sidenav';
import { SidebarProvider, SidebarTrigger } from './components/ui/sidebar';

export default function App() {
  return (
    <SidebarProvider>
      <Sidenav />
      <main className='w-full bg-zinc-800 h-full' >
        <SidebarTrigger />
      </main>
    </SidebarProvider>
  );
}