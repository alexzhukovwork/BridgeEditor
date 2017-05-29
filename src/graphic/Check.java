package graphic;

public class Check {
    static boolean supportThick(double supportThick, double lowerSupportThick){
        boolean result = false;
        
        if(supportThick <= lowerSupportThick && supportThick > 0)
            result = true;
        
        return result;
    }
    
    static boolean supportHeight(double supportHeight, double bridgLevel){
        boolean result = false;
        
        if(supportHeight > bridgLevel + 20 && bridgLevel > 0)
            result = true;
        
        return result;
    }
    
    static boolean blindingHeight(double blindingHeight){
        boolean result = false;
        
        if(blindingHeight > 0)
            result = true;
        
        return result;
    }
    
    static boolean lowerSupportThick(double lowerSupportThick){
        boolean result = false;
        
        if(lowerSupportThick > 0)
            result = true;
        
        return result;
    }
    
    static boolean lowerSupportHeight(double lowerSupportHeight){
        boolean result = false;
        
        if(lowerSupportHeight > 0)
            result = true;
        
        return result;
    }
    
    static boolean lowerSupportWidth(double lowerSupportWidth){
        boolean result = false;
        
        if(lowerSupportWidth > 0)
            result = true;
        
        return result;
    }
    
    static boolean bridgeLenght(double bridgeLenght){
        boolean result = false;
        
        if(bridgeLenght > 0)
            result = true;
        
        return result;
    }
    
    static boolean bridgeWidth(double bridgeWidth, double supportWidth, double lowerSupportWidth){
        boolean result = false;
        
        if(bridgeWidth > 0 && (bridgeWidth >= lowerSupportWidth / 2.5 - supportWidth
                && bridgeWidth <= lowerSupportWidth / 2.5 + supportWidth) )
            result = true;
        
        return result;
    }
    
    static boolean bridgeLevel(double bridgeLevel, double supportHeight){
        boolean result = false;
        
        if(bridgeLevel > 0 && bridgeLevel + 20 < supportHeight)
            result = true;
        
        return result;
    }
    
    static boolean metalWidth(double metalWidth, double bridgeWidth){
        boolean result = false;
        
        if(metalWidth > 0 && metalWidth <= bridgeWidth / 2)
            result = true;
        
        return result;
    }
    
    static boolean metalHeight(double metalHeight, double bridgeLevel){
        boolean result = false;
        
        if(metalHeight > 0 && metalHeight < bridgeLevel)
            result = true;
        
        return result;
    }
    
    static boolean holes(double firstHole, double secondHole, double thirdHole, 
            double supportHeight, double bridgeLevel, double bridgeHeight, int count){
        boolean result = false;
        double needHeight = firstHole + secondHole + thirdHole * count + 5 * count + 2 * count;
        double canHeight = supportHeight - bridgeLevel - bridgeHeight;
        if(firstHole > 0 && secondHole > 0 && thirdHole > 0 && needHeight <= canHeight)
            result = true;
        
        return result;
    }
    
}
