<?php
/**
 * php extends singleton
 * 
 * @author Binbin Ma
 * @version 20150807
 * 
 */
class Cache {
    
    private static $_instance;
    protected function __construct() { 
    } 

    final public static function getInstance($className = null) {
        $calledClassName = $className or $calledClassName = get_called_class();
        if (!self::$_instance[$calledClassName]){
            self::$_instance[$calledClassName] = new $calledClassName();
        }
        return self::$_instance[$calledClassName];
    } 

    final private function __clone() { 
    } 
}
class MemCache extends Cache {
    public function getName() {
        echo 'Mem';
    }
}

// 5.3 ago
echo MemCache::getInstance('MemCache')->getName();
// 5.3 later
echo MemCache::getInstance()->getName();
