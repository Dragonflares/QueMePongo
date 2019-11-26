function obtenerMaterialesCompatibles(materiales, materialesNoCompatibles){
    var materialesCompatibles = [];
    for( var material of materiales)
    {
        if(materialesNoCompatibles.contains(material))
        {
            
        }
        else
        {
            materialesCompatibles.add(material);
        }
    }
    return materialesCompatibles;
}