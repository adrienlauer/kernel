/**
 * This file is part of Nuun IO Kernel Core.
 * <p>
 * Nuun IO Kernel Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Nuun IO Kernel Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with Nuun IO Kernel Core.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.nuun.kernel.core.internal;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

import io.nuun.kernel.api.di.UnitModule;
import io.nuun.kernel.api.plugin.request.RequestType;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScanResults implements Serializable {
    private final Set<URL> urls = new HashSet<>();
    private final List<RoundResults> results = new ArrayList<>();

    protected static class Key {
        private final RequestType type;
        private final Object key;

        public Key(RequestType type, Object key) {
            this.type = type;
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key1 = (Key) o;
            return type == key1.type &&
                    Objects.equals(key, key1.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, key);
        }
    }

    public Map<Class<?>, Collection<Class<?>>> scannedSubTypesByParentClass() {
        return concatMaps(RoundResults::scannedSubTypesByParentClass);
    }

    public Map<String, Collection<Class<?>>> scannedSubTypesByParentRegex() {
        return concatMaps(RoundResults::scannedSubTypesByParentRegex);
    }

    public Map<String, Collection<Class<?>>> scannedTypesByRegex() {
        return concatMaps(RoundResults::scannedTypesByRegex);
    }

    public Map<Predicate<Class<?>>, Collection<Class<?>>> scannedTypesByPredicate() {
        return concatMaps(RoundResults::scannedTypesByPredicate);
    }

    public Map<Class<? extends Annotation>, Collection<Class<?>>> scannedClassesByAnnotationClass() {
        return concatMaps(RoundResults::scannedClassesByAnnotationClass);
    }

    public Map<String, Collection<Class<?>>> scannedClassesByAnnotationRegex() {
        return concatMaps(RoundResults::scannedClassesByAnnotationRegex);
    }

    public Map<String, Collection<String>> getPropertiesFilesByPrefix() {
        return concatMaps(RoundResults::getPropertiesFilesByPrefix);
    }

    public Map<String, Collection<String>> getResourcesByRegex() {
        return concatMaps(RoundResults::getResourcesByRegex);
    }

    public Collection<Class<?>> getClassesToBind() {
        return concatCollections(RoundResults::getClassesToBind);
    }

    public List<UnitModule> getModules() {
        return concatCollections(RoundResults::getModules);
    }

    public List<UnitModule> getOverridingModules() {
        return concatCollections(RoundResults::getOverridingModules);
    }

    public Collection<String> getPropertyFiles() {
        return concatCollections(RoundResults::getPropertyFiles);
    }

    @SuppressWarnings("unchecked")
    private <K, V> Map<K, V> concatMaps(Function<RoundResults, Map<K, V>> extractor) {
        return unmodifiableMap(Stream.of(results.stream()
                .map(extractor)
                .toArray(Map<?, ?>[]::new))
                .flatMap(map -> ((Map<K, V>) map).entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> concatCollections(Function<RoundResults, Collection<T>> extractor) {
        return unmodifiableList(Stream.of(results.stream()
                .map(extractor)
                .toArray(Collection<?>[]::new))
                .flatMap(col -> ((Collection<T>) col).stream())
                .collect(Collectors.toList()));
    }

    public void addClassesToBind(Collection<Class<?>> classesToBind) {
        this.classesToBind.addAll(classesToBind);
    }

    public void addChildModule(UnitModule module) {
        childModules.add(module);
    }

    public void addChildOverridingModule(UnitModule module) {
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

    public Set<URL> getUrls() {
        return urls;
    }

    public void addUrls(Set<URL> urls) {
        this.urls.addAll(urls);
    }
}
