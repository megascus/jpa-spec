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
package javax.persistence.spi;

/**
 * {@link ProviderUtil} SPIメソッドによって返されたロード状態です。
 * @since Java Persistence 2.0
 *
 */
public enum LoadState {
    /** 要素はロードされている状態だと知られています。 */
    LOADED,
    /** 要素はロードされていない状態だと知られています。 */
    NOT_LOADED,
    /** 要素のロード状態は測定できません。 */
    UNKNOWN
}
