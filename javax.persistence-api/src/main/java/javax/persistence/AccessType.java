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
package javax.persistence;

/**
 * エンティティクラスやマップドスーパークラス、組み込みクラス、もしくはそれらのようなクラスの特定の属性に適用されるアクセス型を指定するために{@link Access} アノテーションと共に使用されます。
 * 
 * @see Access
 *
 * @since Java Persistence 2.0
 */
public enum AccessType {

    /** フィールドベースのアクセスが使用されます。 */
    FIELD,

    /** プロパティーベースのアクセスが使用されます。 */
    PROPERTY
}
