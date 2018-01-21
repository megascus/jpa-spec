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

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <code>java.util.Map</code>型の関連付けのマップキーの型を指定します。
 * 
 * マップキーは基本型や組み込みクラス、もしくはエンティティにすることができます。
 * マップがJavaジェネリクスを使用して指定されている場合は、<code>MapKeyClass</code>アノテーションおよび関連型を指定する必要はありません。
 * それ以外の場合は指定する必要があります。
 * 
 * <code>MapKeyClass</code>アノテーションは<code>ElementCollection</code>やコレクション値のリレーションシップのアノテーション(<code>OneToMany</code>または<code>ManyToMany</code>)
 * と共に使用されます。
 * <code>MapKey</code>アノテーションと<code>MapKeyClass</code>アノテーションは同時に使われることはありません。
 *
 * <pre>
 *
 *    Example 1:
 *
 *    &#064;Entity
 *    public class Item {
 *       &#064;Id int id;
 *       ...
 *       &#064;ElementCollection(targetClass=String.class)
 *       &#064;MapKeyClass(String.class)
 *       Map images;  // imageの名前からimageのファイル名へのmap
 *       ...
 *    }
 *
 *    Example 2:
 *
 *    // MapKeyClassとリレーションシップのターゲット型がデフォルトにできる場合
 *
 *    &#064;Entity
 *    public class Item {
 *       &#064;Id int id;
 *       ...
 *       &#064;ElementCollection
 *       Map&#060;String, String&#062; images; 
 *        ...
 *     }
 *
 *     Example 3:
 *
 *     &#064;Entity
 *     public class Company {
 *        &#064;Id int id;
 *        ...
 *        &#064;OneToMany(targetEntity=com.example.VicePresident.class)
 *        &#064;MapKeyClass(com.example.Division.class)
 *        Map organization;
 *     }
 *
 *     Example 4:
 *
 *     // MapKeyClassとリレーションシップのターゲット型がデフォルトにできる場合
 *
 *     &#064;Entity
 *     public class Company {
 *        &#064;Id int id;
 *        ...
 *        &#064;OneToMany
 *        Map&#060;Division, VicePresident&#062; organization;
 *     }
 *
 * </pre>
 * @see ElementCollection 
 * @see OneToMany
 * @see ManyToMany
 * @since Java Persistence 2.0
 */

@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface MapKeyClass {
	/**(必須) マップキーの型*/
	Class value();
}
