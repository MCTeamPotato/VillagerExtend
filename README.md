### Villager Extend - LocalBully
**Transform Villager Interactions with Twisted Loyalty!**

Tired of villagers hating you for "accidentally" harming them? This mod flips vanilla mechanics on their head, making villagers **admire you** for your bullying behavior! Perfect for chaotic players who want to rule villages with fear... and unexpected loyalty.

---

### 🔥 Key Features
#### **Hurt = Affection**
- When a villager's health drops **below 50%**, they grant **MAJOR_POSITIVE gossip** instead of anger.
- The closer they are to death, the **more reputation** you gain! *(Example: At 10% health, they grant +40 reputation!)*

#### **Death = Eternal Devotion**
- **Killing a villager** now showers you with **MAJOR_POSITIVE gossip**—no more negative consequences!

#### **Stealthy Bullying**
- Angry particles **don’t appear** if the villager is below 50% health. Suffer in silence!

---

### Code Snippet Teaser
```java
// Hurt a villager below 50% health? Gain loyalty!  
if (percent < 0.5) {  
    addGossip(MAJOR_POSITIVE, 50 - (int)(percent * 100F)); // Lower health = MORE reputation!  
}  

// Kill a villager? They die loving you!  
onDie() {  
    return GossipType.MAJOR_POSITIVE; // Who said murder can't be profitable?  
}  
```  

---  
**Embrace the chaos. Rule through fear.**  
*Disclaimer: Not endorsed by actual villagers. May cause existential guilt.*