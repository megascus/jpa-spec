/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence.metamodel;

/**
 * <code>PluralAttribute</code>型のインスタンスは永続化されたコレクションの値の属性を表します。
 *
 * @param <X> 表されるコレクションが属する型
 * @param <C> 表されるコレクションの型
 * @param <E> 表されるコレクションの要素の型
 *
 * @since Java Persistence 2.0
 */
public interface PluralAttribute<X, C, E> 
		extends Attribute<X, C>, Bindable<E> {
	
	public static enum CollectionType {

	    /** コレクションの値の属性 */
	    COLLECTION, 

	    /** セットの値の属性 */
	    SET, 

	    /** リストの値の属性 */
	    LIST, 

	    /** マップの値の属性 */
	    MAP
	}
		
    /**
     * コレクションの型を返します。
     * @return コレクションの型
     */
    CollectionType getCollectionType();

    /**
     * コレクションの要素型を表す型を返します。
     * @return 属性の型
     */
    Type<E> getElementType();
}
