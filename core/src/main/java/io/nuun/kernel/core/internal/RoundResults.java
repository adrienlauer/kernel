package io.nuun.kernel.core.internal;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

import io.nuun.kernel.api.di.UnitModule;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class RoundResults
{
    private final List<UnitModule> childModules = new ArrayList<>();
    private final List<UnitModule> childOverridingModules = new ArrayList<>();

    private final Set<Class<?>> classesToBind = new HashSet<>();
    private final Map<Class<?>, Collection<Class<?>>> mapSubTypes = new HashMap<>();
    private final Map<String, Collection<Class<?>>> mapSubTypesByName = new HashMap<>();
    private final Map<String, Collection<Class<?>>> mapTypesByName = new HashMap<>();
    private final Map<Predicate<Class<?>>, Collection<Class<?>>> mapTypesByPredicate = new HashMap<>();
    private final Map<Class<? extends Annotation>, Collection<Class<?>>> mapAnnotationTypes = new HashMap<>();
    private final Map<String, Collection<Class<?>>> mapAnnotationTypesByName = new HashMap<>();
    private final Map<String, Collection<String>> propertyFilesByPrefix = new HashMap<>();
    private final Map<String, Collection<String>> resourcesByRegex = new HashMap<>();
    private final Collection<String> propertyFiles = new HashSet<>();

    public Map<Class<?>, Collection<Class<?>>> scannedSubTypesByParentClass()
    {
        return unmodifiableMap(mapSubTypes);
    }

    public Map<String, Collection<Class<?>>> scannedSubTypesByParentRegex()
    {
        return unmodifiableMap(mapSubTypesByName);
    }

    public Map<String, Collection<Class<?>>> scannedTypesByRegex()
    {
        return unmodifiableMap(mapTypesByName);
    }

    public Map<Predicate<Class<?>>, Collection<Class<?>>> scannedTypesByPredicate()
    {
        return unmodifiableMap(mapTypesByPredicate);
    }

    public Map<Class<? extends Annotation>, Collection<Class<?>>> scannedClassesByAnnotationClass()
    {
        return unmodifiableMap(mapAnnotationTypes);
    }

    public Map<String, Collection<Class<?>>> scannedClassesByAnnotationRegex()
    {
        return unmodifiableMap(mapAnnotationTypesByName);
    }

    public Map<String, Collection<String>> getPropertiesFilesByPrefix()
    {
        return unmodifiableMap(propertyFilesByPrefix);
    }

    public Map<String, Collection<String>> getResourcesByRegex()
    {
        return unmodifiableMap(resourcesByRegex);
    }

    public Collection<Class<?>> getClassesToBind()
    {
        return unmodifiableCollection(classesToBind);
    }

    public List<UnitModule> getModules()
    {
        return unmodifiableList(childModules);
    }

    public List<UnitModule> getOverridingModules()
    {
        return unmodifiableList(childOverridingModules);
    }

    public Collection<String> getPropertyFiles()
    {
        return unmodifiableCollection(propertyFiles);
    }

    public void addClassesToBind(Collection<Class<?>> classesToBind) {
        this.classesToBind.addAll(classesToBind);
    }

    public void addChildModule(UnitModule module)
    {
        childModules.add(module);
    }

    public void addChildOverridingModule(UnitModule module)
    {
        childOverridingModules.add(module);
    }

    public void addSubtypes(Class<?> parentType, Collection<Class<?>> subtypes) {
        mapSubTypes.put(parentType, subtypes);
    }

    public void addSubTypesByName(String typeName, Collection<Class<?>> subtypes) {
        mapSubTypesByName.put(typeName, subtypes);
    }

    public void addTypesByName(String typeName, Collection<Class<?>> subtypes) {
        mapTypesByName.put(typeName, subtypes);
    }

    public void addTypesByPredicate(Predicate<Class<?>> classPredicate, Collection<Class<?>> subtypes) {
        mapTypesByPredicate.put(classPredicate, subtypes);
    }

    public void addAnnotationTypes(Class<? extends Annotation> annotationClass, Collection<Class<?>> subtypes) {
        mapAnnotationTypes.put(annotationClass, subtypes);
    }

    public void addAnnotationTypesByName(String annotationName, Collection<Class<?>> subtypes) {
        mapAnnotationTypesByName.put(annotationName, subtypes);
    }

    public void addResourcesByRegex(String regex, Set<String> urls) {
        resourcesByRegex.put(regex, urls);
    }

    public void addPropertyFiles(Set<String> propertyFiles) {
        propertyFiles.addAll(propertyFiles);
    }

    public void addPropertyFilesByPrefix(String prefix, Set<String> propertyFiles) {
        propertyFilesByPrefix.put(prefix, propertyFiles);
    }
}
