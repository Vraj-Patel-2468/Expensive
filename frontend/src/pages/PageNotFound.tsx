import { Button } from "@/components/ui/button";
import { ArrowLeftIcon } from "@radix-ui/react-icons";
import { useNavigate } from "react-router";
import FuzzyText from "@/animation/fuzzyText";

export default function PageNotFound () {
    const navigate = useNavigate();

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-background px-4 dark">
            <div className="flex flex-col items-center gap-4">
                <FuzzyText
                    baseIntensity={0.2}
                    hoverIntensity={0.4} 
                    enableHover={true}
                >
                    404
                </FuzzyText>
                <h1 className="text-3xl font-bold text-foreground">Page Not Found</h1>
                <p className="text-muted-foreground text-center max-w-md">
                    Sorry, the page you are looking for does not exist or has been moved.
                </p>
                <Button
                    variant="default"
                    className="mt-4 flex items-center gap-2"
                    onClick={() => navigate("/")}
                >
                    <ArrowLeftIcon className="w-4 h-4" />
                    Go to S0ggy Home
                </Button>
            </div>
        </div>
    );
}

