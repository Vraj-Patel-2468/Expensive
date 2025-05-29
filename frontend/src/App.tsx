import { BrowserRouter, Routes, Route } from "react-router";
import PageNotFound from "./pages/PageNotFound";
import Index from "./pages/Index";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path = "/" element = {<Index />} />
        <Route path = "/*" element = {<PageNotFound />} />
      </Routes>
    </BrowserRouter>
  );
}