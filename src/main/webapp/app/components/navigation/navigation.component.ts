import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot, NavigationEnd } from '@angular/router';

@Component({
    selector: 'jhi-navigation-bar',
    templateUrl: './navigation.component.html',
    styleUrls: ['navigation.scss']
})
export class NavigationComponent implements OnInit {
    url: string;
    activeLayer: number;

    firstDotImage: string;
    secondDotImage: string;
    thirdDotImage: string;
    homePageUrl: string;
    championListUrl: string;
    memberListUrl: string;
    championDetailsUrl: string;
    memberDetailsUrl: string;
    firstDotUrl: string;
    secondDotUrl: string;
    firstDotLabel: string;

    secondDotLabel: string;
    thirdDotLabel: string;

    constructor(private router: Router) {
        this.firstDotImage = '../../../content/images/ellipse-empty.png';
        this.secondDotImage = '../../../content/images/ellipse-empty.png';
        this.thirdDotImage = '../../../content/images/ellipse-empty.png';
        this.homePageUrl = '/';
        this.championListUrl = '/register';
        this.memberListUrl = '/register';
        this.championDetailsUrl = '/accessdenied';
        this.memberDetailsUrl = '/accessdenied';
        this.firstDotUrl = '/register';
        this.firstDotLabel = 'HOME PAGE';
    }

    ngOnInit() {
        this.router.events.subscribe(event => {
            if (event instanceof NavigationEnd) {
                this.url = this.router.routerState.snapshot.url;
                if (this.url === this.homePageUrl) {
                    this.setFirstLayerActive();
                } else if (this.url === this.championListUrl || this.url === this.memberListUrl) {
                    this.setSecondLayerActive();
                } else if (this.url === this.championDetailsUrl || this.url === this.memberDetailsUrl) {
                    this.setThirdLayerActive();
                }
                console.log(this.url);
            }
        });
    }

    private setFirstLayerActive() {
        console.log('setting First Layer');
        this.activeLayer = 1;
        this.firstDotImage = '../../../content/images/ellipse-full.png';
        this.secondDotImage = '../../../content/images/ellipse-empty.png';
        this.thirdDotImage = '../../../content/images/ellipse-empty.png';

        this.secondDotLabel = '';
        this.thirdDotLabel = '';
    }

    private setSecondLayerActive() {
        console.log('setting Second Layer');
        this.activeLayer = 2;
        this.firstDotImage = '../../../content/images/ellipse-full.png';
        this.secondDotImage = '../../../content/images/ellipse-full.png';
        this.thirdDotImage = '../../../content/images/ellipse-empty.png';

        if (this.url === this.championListUrl) {
            this.secondDotLabel = 'CHAMPIONS LIST';
        }
        if (this.url === this.memberListUrl) {
            this.secondDotLabel = 'MEMBERS LIST';
        }
        this.thirdDotLabel = '';
    }

    private setThirdLayerActive() {
        console.log('setting Third Layer');
        this.activeLayer = 3;
        this.firstDotImage = '../../../content/images/ellipse-full.png';
        this.secondDotImage = '../../../content/images/ellipse-full.png';
        this.thirdDotImage = '../../../content/images/ellipse-full.png';

        if (this.url === this.championDetailsUrl) {
            this.secondDotLabel = 'CHAMPIONS LIST';
        }
        if (this.url === this.memberDetailsUrl) {
            this.secondDotLabel = 'MEMBERS LIST';
        }
        this.thirdDotLabel = 'DETAILS PAGE';
    }

    getFirstDotImageUrl() {
        return this.firstDotImage;
    }
    getSecondDotImageUrl() {
        return this.secondDotImage;
    }
    getThirdDotImageUrl() {
        return this.thirdDotImage;
    }

    getFirstDotUrl() {
        if (this.activeLayer === 2 || this.activeLayer === 3) {
            console.log('redirecting to home page');
            this.router.navigate(['/']);
        }
    }

    getSecondDotUrl() {
        if (this.activeLayer === 3) {
            if (this.url === this.championDetailsUrl) {
                this.router.navigate([this.championListUrl]);
            }
            if (this.url === this.memberDetailsUrl) {
                this.router.navigate([this.memberListUrl]);
            }
            console.log('redirecting to home page');
        }
    }

    getThirdDotUrl() {}
}
