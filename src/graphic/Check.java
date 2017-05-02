/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

/**
 *
 * @author Алексей
 */
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
                || bridgeWidth <= lowerSupportWidth / 2.5 + supportWidth) )
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
    
}